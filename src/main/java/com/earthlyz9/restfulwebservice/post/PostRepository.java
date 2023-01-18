package com.earthlyz9.restfulwebservice.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  // 선언하는 것만으로도 기본적인 crud 가능
  // 동작을 커스텀해야한다면 오버라이딩해서 사용
}
