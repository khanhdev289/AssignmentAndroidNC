package khanhnqph30151.fptpoly.assignment.newspaper;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.model.TinTuc;

public class TinTucLoader {
    ArrayList<TinTuc> tinTucList = new ArrayList<TinTuc>();
    TinTuc tinTuc;
    String textContent;

    public ArrayList<TinTuc> getTinTucList(InputStream inputStream) {
        // nội dung tự viết , tham khảo ví dụ product
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // truyền nguồn dữ liệu
            parser.setInput(inputStream, null);
            // xác định event type
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // viết code xử lý ở đây
                String tagName = parser.getName();
                Log.d("zzzzz", "Tag name =  " + tagName +
                        ", Độ sâu của thẻ = " + parser.getDepth() + ", event = " + eventType);


                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        // bắt đầu vào 1 thẻ
                        if (tagName.equalsIgnoreCase("item")) {
                            tinTuc = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textContent = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(tinTuc != null){
                            //
                            if(tagName.equalsIgnoreCase("item")){
                                tinTucList.add(tinTuc);
                            }

                            if (tagName.equalsIgnoreCase("title")){
                                tinTuc.setTitle(textContent);
                            }

                            if (tagName.equalsIgnoreCase("description"))
                            {
                                Log.d("aaaaaa", "getTinTucList: them des");
                                tinTuc.setDescription(textContent);
//                                org.jsoup.nodes.Document doc = Jsoup.parse(temp);
//                                Elements ImgTag = doc.select("img");
//                                for (Element img : ImgTag) {
//                                    String src = img.attr("src");
//                                    item.setImgAvatar(src);
//                                }
//                                String text = temp.replaceAll("<[^>]*>", "");
//
//                                item.setDescription(text);
                            }
                            if (tagName.equalsIgnoreCase("pubDate"))
                            {
                                Log.d("aaaaaa", "getTinTucList: them pubdate");
                                tinTuc.setPubDate(textContent);
                            }
                            if (tagName.equalsIgnoreCase("link"))
                            {
                                Log.d("aaaaaa", "getTinTucList: them link");
                                tinTuc.setLink(textContent);
                            }

                        }
                        break;
                    default:
                        Log.d("zzzz", "eventType khác: " + eventType + ", tag = " + tagName);
                        break;
                }


                // viết lệnh chuyển event type để vòng lặp không bị treo
                // để ở cuối cùng của lệnh while
                eventType = parser.next();
            }
            inputStream.close();


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tinTucList;
    }
}
