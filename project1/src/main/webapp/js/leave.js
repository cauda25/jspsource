/**
 * 
 */
// 회원탈퇴 클릭 시 form submit 중지
document.querySelector("form").addEventListener("submit",(e)=>{
	e.preventDefault();
// confirm(정말로 탈퇴하시겠습니까?)

// 확인 : true, 최소:false
//const result = confirm("정말로 탈퇴하시겠습니까?");
//console.log(result);

// 확인인 경우 폼 submit 하기
if(confirm("정말로 탈퇴하시겠습니까?")){
e.target.submit();
}
});