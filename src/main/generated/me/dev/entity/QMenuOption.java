package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuOption is a Querydsl query type for MenuOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuOption extends EntityPathBase<MenuOption> {

    private static final long serialVersionUID = 341876816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuOption menuOption = new QMenuOption("menuOption");

    public final StringPath category = createString("category");

    public final StringPath des = createString("des");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMenu menu;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> optionPrice = createNumber("optionPrice", Integer.class);

    public QMenuOption(String variable) {
        this(MenuOption.class, forVariable(variable), INITS);
    }

    public QMenuOption(Path<? extends MenuOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuOption(PathMetadata metadata, PathInits inits) {
        this(MenuOption.class, metadata, inits);
    }

    public QMenuOption(Class<? extends MenuOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
    }

}

