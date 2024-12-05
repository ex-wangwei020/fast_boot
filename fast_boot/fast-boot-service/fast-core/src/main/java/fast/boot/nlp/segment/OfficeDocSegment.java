package fast.boot.nlp.segment;

import fast.boot.entity.vo.DocSegmentInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.jpmml.model.visitors.NodeFilterer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.springframework.util.CollectionUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将doc、docx文件通过标题进行文章分段-这里只使用了标准的h1-h6来进行区分
 * 如果有自定义的标签，请使用classname+tagname来区分
 */
public class OfficeDocSegment {

    private static final String[] headings = {"h1","h2","h3","h4","h5","h6"};

    private boolean withHtag;

    public OfficeDocSegment(){
        this.withHtag = true;
    };

    public OfficeDocSegment(boolean withHtag){
        this.withHtag = withHtag;
    }

    /**
     * 获取分段的结果
     * @param file
     * @return
     */
    public List<DocSegmentInfo> segment(File file) throws TikaException, IOException, SAXException {
        List<String> list = new ArrayList<>();
        List<Integer> hList = new ArrayList<>();
        List<DocSegmentInfo> docList = new ArrayList<>();
        //通过tika获取文档对应的html格式内容
        String htmlContent = parseToHTML(file);
        //通过Jsoup截取h1 h2 h3 h4 h5 h6的内容
        Document doc = Jsoup.parse(htmlContent);
        Elements elements = doc.getAllElements();
        for(Element element : elements){
            String tagName = element.tagName();
            String className = element.className();
            String content = element.text();
            if(StringUtils.isBlank(content)){
                continue;
            }
            //过滤页眉页脚
            if(StringUtils.equals(element.tagName(), "p") &&
                    (StringUtils.equals(element.className(), "header")||StringUtils.equals(element.className(), "footer"))){
                continue;
            } else if (Arrays.asList(headings).contains(tagName)) {//如果是标题
                list.add(element.text());
                hList.add(list.size()-1);
            }else if(StringUtils.equals(element.tagName(), "p")){
                list.add(element.text());
            }
        }

        for (int i = 0; i < hList.size(); i++) {
            int idx = hList.get(i);
            DocSegmentInfo docSegmentInfo = new DocSegmentInfo();
            docSegmentInfo.setHeadingTitle(list.get(idx));
            if (i+1<hList.size()) {
                List<String> subList = list.subList(idx+1, hList.get(i+1));
                docSegmentInfo.setContent(String.join("\n", subList));
                docList.add(docSegmentInfo);
            }else{
                List<String> subList = list.subList(idx+1, list.size());
                docSegmentInfo.setContent(String.join("\n", subList));
                docList.add(docSegmentInfo);
            }
        }

        return docList;
    }

    public String parseToHTML(File file) throws IOException, SAXException, TikaException {
        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = Files.newInputStream(file.toPath())){
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }
}
