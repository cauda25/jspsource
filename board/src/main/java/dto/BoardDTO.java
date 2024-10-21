package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
	
	private int bno;
	private String name;
	private String password;
	private String title;
	private String content;
	private String fileF;
	private int reRef;
	private int reLev;
	private int reSeq;
	private int readcnt;
	private Date regdate;

}
