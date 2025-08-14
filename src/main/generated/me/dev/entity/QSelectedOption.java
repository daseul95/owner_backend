package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSelectedOption is a Querydsl query type for SelectedOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSelectedOption extends EntityPathBase<SelectedOption> {

    private static final long serialVersionUID = -1227787636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSelectedOption selectedOption = new QSelectedOption("selectedOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOption option;

    public final NumberPath<Integer> optionPrice = createNumber("optionPrice", Integer.class);

    public final QOrderMenu orderMenu;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QSelectedOption(String variable) {
        this(SelectedOption.class, forVariable(variable), INITS);
    }

    public QSelectedOption(Path<? extends SelectedOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSelectedOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSelectedOption(PathMetadata metadata, PathInits inits) {
        this(SelectedOption.class, metadata, inits);
    }

    public QSelectedOption(Class<? extends SelectedOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option = inits.isInitialized("option") ? new QOption(forProperty("option")) : null;
        this.orderMenu = inits.isInitialized("orderMenu") ? new QOrderMenu(forProperty("orderMenu"), inits.get("orderMenu")) : null;
    }

}

