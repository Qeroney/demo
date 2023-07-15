package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomUser is a Querydsl query type for CustomUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomUser extends EntityPathBase<CustomUser> {

    private static final long serialVersionUID = 213204571L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomUser customUser = new QCustomUser("customUser");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final QCart cart;

    public final StringPath email = createString("email");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QCustomUser(String variable) {
        this(CustomUser.class, forVariable(variable), INITS);
    }

    public QCustomUser(Path<? extends CustomUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomUser(PathMetadata metadata, PathInits inits) {
        this(CustomUser.class, metadata, inits);
    }

    public QCustomUser(Class<? extends CustomUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCart(forProperty("cart")) : null;
    }

}

