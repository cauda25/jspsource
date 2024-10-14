/**
 * 
 */
// 로그인이 되었다면, 전체회원조회 매뉴의 클래스명 disabled 제거

if(userid){
	const list = document.querySelector(".dropdown-menu li a");
	list.classList.remove("disabled");
}