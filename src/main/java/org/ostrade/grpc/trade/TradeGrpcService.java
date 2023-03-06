package org.ostrade.grpc.trade;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.ostrade.proto.common.field.AssetClass;
import org.ostrade.proto.trade.Trade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@GrpcService
public class TradeGrpcService implements TradeGrpc {
    @Override
    public Uni<Trade> getTrade(UTI request) {
        Objects.requireNonNull(request.getValue(), "UTI is required");
        Trade trade = Trade.newBuilder()
                .setUti(request.getValue())
                .setTradeDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .setAssetClass(AssetClass.ASSET_CLASS_COMMODITY)
                .build();
        return Uni.createFrom().item(trade);
    }

}
