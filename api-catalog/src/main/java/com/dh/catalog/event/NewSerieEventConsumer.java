package com.dh.catalog.event;

import com.dh.catalog.model.Serie;
import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.repository.SerieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class NewSerieEventConsumer {

    private final SerieRepositoryMongo serieRepositoryMongo;

    public NewSerieEventConsumer(SerieRepositoryMongo serieRepositoryMongo) {
        this.serieRepositoryMongo = serieRepositoryMongo;
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIE)
    public void execute(NewSerieEventConsumer.Data data) {
        Serie serieNew= new Serie();
        BeanUtils.copyProperties(data.getSerie(),serieNew);
        serieRepositoryMongo.deleteById(data.getSerie().getId());
        serieRepositoryMongo.save(serieNew);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private Data.SerieDto serie = new SerieDto();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SerieDto implements Serializable {

            @Serial
            private static final long serialVersionUID = 1L;
            private Long id;
            private String name;
            private String genre;
            private String urlStream;

        }

    }
}
