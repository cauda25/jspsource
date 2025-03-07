/**
 * 
 */
// 목록 클릭 시 리스트로 이동
document.querySelector(".btn-primary").addEventListener("click",()=>{
	location.href = "list_pro.jsp";
});

document.querySelector(".btn-danger").addEventListener("click",()=>{
	location.href = "delete_pro.jsp?code="+code;
});
// 수정 클릭 시 price의 값이 숫자가 들어있는지 확인
document.querySelector("body div:nth-child(3) form").addEventListener("submit",(e)=>{
		e.preventDefault();
	const price = document.querySelector("#price");
	// * : 0 ~ 무한대
	const regEx = /^[0-9]{3,10}$/;
	if(!regEx.test(price.value)){
		alert("가격을 입력해주세요");
		//price.focus();
		price.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;
	}
	// 이상이 없는 경우 form 전송
	e.target.submit();
});