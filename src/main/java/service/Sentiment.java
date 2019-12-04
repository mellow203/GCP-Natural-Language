package service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;

public class Sentiment {

    public static void main(String... args) throws Exception {
        //감정 분석
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            // The text to analyze
            String text = "스타벅스는 디저트는 맛있지만 가격이 비싸서 다신 안간다";
            Document doc = Document.newBuilder()
                    .setContent(text).setType(Document.Type.PLAIN_TEXT).build();

            // Detects the sentiment of the text
            com.google.cloud.language.v1.Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

            System.out.printf("Text: %s%n", text);
            System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
        }
    }
}
