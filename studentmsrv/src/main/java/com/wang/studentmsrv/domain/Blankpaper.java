package com.wang.studentmsrv.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * for using JSON here
 * <dependency>
 * 	<groupId>com.vladmihalcea</groupId>
 * 	<artifactId>hibernate-types-52</artifactId>
 * 	<version>2.12.0</version>
 * </dependency>
 */
@TypeDefs({@TypeDef(name = "json", typeClass = JsonType.class)})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blankpaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private Long examId; // fk exam in teachermsrv
    @Type(type = "json")
    @Column(name = "paper_content", columnDefinition = "json")
    private Map<String,Object> paperContent = new HashMap<>();
    public Blankpaper(long examId, Map<String,Object> paperContent) {
        this.examId = examId;
        this.paperContent = paperContent;
    }
}
