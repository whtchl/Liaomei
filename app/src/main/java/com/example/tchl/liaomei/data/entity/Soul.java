package com.example.tchl.liaomei.data.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Unique;

import java.io.Serializable;

/**
 * Created by tchl on 2016-05-26.
 */
public class Soul implements Serializable {
    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    @Column("_id")
    public long id;
    // @NotNull
    @Unique
    @Column("objectId")
    public String objectId;
}
