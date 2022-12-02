package com.wang.exammsv.domain;

import com.wang.exammsv.domain.Blankpaper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlankpaperRepository extends CrudRepository<Blankpaper, Long> {
    /**
     * if the examId exists, cover it
     * @param bp
     */
    public default void saveUnique(Blankpaper bp) {
        var oldBp = findByExamId(bp.getExamId()).stream().findFirst();
        if (oldBp.isPresent()) {
            oldBp.get().setPaperContent(bp.getPaperContent());
            save(oldBp.get());
        } else {
            save(bp);
        }
    }

    List<Blankpaper> findByExamId(long examId);
}
