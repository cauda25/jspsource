package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class MemberDTO {
	private String userid;
	private String name;
	private String password;
	private int age;
	private String email;
}
