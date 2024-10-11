package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class empDTO {
	private int empno;
	private String ename;
	private String jod;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;

	// 생성자/getter/setter/toString

}
