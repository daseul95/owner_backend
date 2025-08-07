package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSelectedMenuOption is a Querydsl query type for SelectedMenuOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSelectedMenuOption extends EntityPathBase<SelectedMenuOption> {

    private static final long serialVersionUID = -1036726965L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSelectedMenuOption selectedMenuOption = new QSelectedMenuOption("selectedMenuOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMenuOption menuOption;

    public final NumberPath<Integer> optionPrice = createNumber("optionPrice", Integer.class);

    public final QOrderMenu orderMenu;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QSelectedMenuOption(String variable) {
        this(SelectedMenuOption.class, forVariable(variable), INITS);
    }

    public QSelectedMenuOption(Path<? extends SelectedMenuOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSelectedMenuOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSelectedMenuOption(PathMetadata metadata, PathInits inits) {
        this(SelectedMenuOption.class, metadata, inits);
    }

    public QSelectedMenuOption(Class<? extends SelectedMenuOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menuOption = inits.isInitialized("menuOption") ? new QMenuOption(forProperty("menuOption"), inits.get("menuOption")) : null;
        this.orderMenu = inits.isInitialized("orderMenu") ? new QOrderMenu(forProperty("orderMenu"), inits.get("orderMenu")) : null;
    }

}

