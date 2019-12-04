package service;

import java.util.Map;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Sentiment;

public class Entities {

    public static void main(String... args) throws Exception {

        //항목 분석
        String text = "선릉역에는 카페가 많다. 최근 할리스에서는 연예인 김종국을 만났다.";
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Document.Type.PLAIN_TEXT)

                    .build();
            AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeEntitiesResponse response = language.analyzeEntities(request);

            // Print the response
                for (Entity entity : response.getEntitiesList()) {
                System.out.printf("Entity: %s", entity.getName());
                System.out.printf("Salience: %.3f\n", entity.getSalience());
                System.out.printf("Type: %s\n", entity.getType());
                System.out.println("Metadata: ");
                for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
                    System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
                }
                for (EntityMention mention : entity.getMentionsList()) {
                    System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
                    System.out.printf("Content: %s\n", mention.getText().getContent());
                    System.out.printf("Type: %s\n\n", mention.getType());
                }
            }
        }
    }
}
