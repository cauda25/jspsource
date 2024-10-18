/**
 * 
 */
// 목록 클릭 시 리스트로 이동
document.querySelector(".btn-primary").addEventListener("click",(e)=>{
	location.href = "/list.do?keyword="+keyword;
})