package com.example.tchl.liaomei.data.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;
import java.util.Date;
/**
 * Created by tchl on 2016-05-26.
 */
@Table("liaomei") public class Liaomei extends Soul{
    @Column("url") public String url;
    @Column("type") public String type;
    @Column("desc") public String desc;
    @Column("who") public String who;
    @Column("used") public boolean used;
    @Column("createdAt") public Date createAt;
    @Column("updatedAt")public Date updatedAt;
    @Column("publishedAt") public Date publidth;
    @Column("imageHeight") public int imageHshedAt;
    @Column("imageWidth") public int imageWieight;


}
