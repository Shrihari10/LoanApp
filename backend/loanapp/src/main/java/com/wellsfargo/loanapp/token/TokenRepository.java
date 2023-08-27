package com.wellsfargo.loanapp.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join EmployeeMaster e\s
      on t.employee.employeeID = e.employeeID\s
      where e.employeeID = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByEmployee(String id);

  Optional<Token> findByToken(String token);
}
