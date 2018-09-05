package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxMusicsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxMusics;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by jjjjj on 2017-10-16.
 */
public class PbxMusicsSpecs  {
    public static Specification<PbxMusics> musicNameEqual(final String musicName) {

        return new Specification<PbxMusics>() {
            @Override
            public Predicate toPredicate(Root<PbxMusics> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("musicName"), "%"+musicName+"%");
            }
        };
    }
    public static Specification<PbxMusics> createSpec(final PbxMusicsCriteria criteria) {
        Specification<PbxMusics> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getMusicName())){
            specs = specs.and(musicNameEqual(criteria.getMusicName()));
        }
        return specs;
    }
}
