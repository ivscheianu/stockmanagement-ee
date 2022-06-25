package com.ivscheianu.stockmanagement.bucket;

import com.ivscheianu.base.persistence.AbstractDO;
import com.ivscheianu.stockmanagement.image.ImageDO;
import com.ivscheianu.stockmanagement.user.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bucket")
@Builder(toBuilder = true)
@ToString(callSuper = true, exclude = "user")
@EqualsAndHashCode(callSuper = true, exclude = "user")
public class BucketDO extends AbstractDO<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserDO user;

    @OneToMany(
        mappedBy = "bucket",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ImageDO> images;
}
