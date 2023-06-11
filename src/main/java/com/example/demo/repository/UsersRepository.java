package com.example.demo.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository;
 
import com.example.demo.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String username);

//	@Query("SELECT u FROM Users u WHERE u.role <> 'admin'")
//	List<Users> findAllUsersExceptAdmin();
	List<Users> findByRoleNot(String role);
	
	@Query("SELECT u FROM Users u WHERE u.role = 0 ")
	List<Users> findAllByUsers();
	
	@Query("SELECT  count(u) FROM Users u")
	Long ListReportNbMembers();

	@Query("SELECT u FROM Users u WHERE u.name LIKE %?1% ")
	List<Users> findAllByNameLike(String key);
	
}
