package service;

import com.google.cloud.language.v1.*;

public class EntitiesSentiment {
    public static void main(String... args) throws Exception {


        //엔티티 감성 분석
        String text = "Hallis sells drinks and sells desserts. The most delicious dessert is cheesecake, but strawberry cake is more delicious these days.";
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text).setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16).build();
            // detect entity sentiments in the given string
            AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
            // Print the response
            for (Entity entity : response.getEntitiesList()) {
                System.out.printf("Entity: %s\n", entity.getName());
                System.out.printf("Salience: %.3f\n", entity.getSalience());
                System.out.printf("Sentiment : %s\n", entity.getSentiment());
                for (EntityMention mention : entity.getMentionsList()) {
                    System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
                    System.out.printf("Content: %s\n", mention.getText().getContent());
                    System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
                    System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
                    System.out.printf("Type: %s\n\n", mention.getType());
                }
            }
        }
    }

}
