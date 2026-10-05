package br.com.unipds.javify.catalogo.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.Arrays;

@ApplicationScoped
public class RelatorioAlbumService {

    @Inject
    MongoClient mongoClient;

    public ResultadoRelatorioDuracaoGenero calcularDuracaoTotalPorGenero(String genero) {
        MongoDatabase database = mongoClient.getDatabase("javify_db");
        MongoCollection<Document> collection = database.getCollection("albuns");

        Document matchStage = new Document("$match", new Document("genero", new Document("$regex", genero).append("$options", "i")));
        Document unwindStage = new Document("$unwind", "$faixas");
        Document groupStage = new Document("$group", new Document("_id", "Gênero Analisado")
                .append("duracaoTotalSegundos", new Document("$sum", "$faixas.duracaoSegundos"))
                .append("totalMusicas", new Document("$sum", 1)));

        Document resultadoQuery = collection.aggregate(Arrays.asList(matchStage, unwindStage, groupStage)).first();

        if (resultadoQuery == null) {
            return new ResultadoRelatorioDuracaoGenero(0L, 0L);
        }

        Long duracao = resultadoQuery.getInteger("duracaoTotalSegundos").longValue();
        Long total = resultadoQuery.getInteger("totalMusicas").longValue();

        return new ResultadoRelatorioDuracaoGenero(duracao, total);
    }

    public record ResultadoRelatorioDuracaoGenero(Long duracaoTotalSegundos, Long totalMusicas) {}
}
