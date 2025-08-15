package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = 270306147L;

    public static final QGroup group = new QGroup("group1");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MenuGroup, QMenuGroup> MenuGroup = this.<MenuGroup, QMenuGroup>createList("MenuGroup", MenuGroup.class, QMenuGroup.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<OptionGroup, QOptionGroup> OptionGroup = this.<OptionGroup, QOptionGroup>createList("OptionGroup", OptionGroup.class, QOptionGroup.class, PathInits.DIRECT2);

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    public QGroup(Path<? extends Group> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata metadata) {
        super(Group.class, metadata);
    }

}

