package com.dodi258.study.domain;


import org.springframework.data.repository.CrudRepository;

public interface PointRedisRepository extends CrudRepository<Point, String> {
}