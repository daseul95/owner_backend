package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuGroup is a Querydsl query type for MenuGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuGroup extends EntityPathBase<MenuGroup> {

    private static final long serialVersionUID = 1250621252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuGroup menuGroup = new QMenuGroup("menuGroup");

    public final QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRequired = createBoolean("isRequired");

    public final QMenu menu;

    public QMenuGroup(String variable) {
        this(MenuGroup.class, forVariable(variable), INITS);
    }

    public QMenuGroup(Path<? extends MenuGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuGroup(PathMetadata metadata, PathInits inits) {
        this(MenuGroup.class, metadata, inits);
    }

    public QMenuGroup(Class<? extends MenuGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
    }

}

