// 제목 클릭시 a 태그 기능 중지
// href에 있는 번호 가져오기 후 actionForm 안 bno value 호 삽입
const af = document.querySelector("#actionForm");

// 제목의 a 태그가 여러개 있는 경우
// 개별로 처리
// const allA = document.querySelectorAll(".table a");
// allA.forEach("a",()=>{
//	a.addEventListener("click",(e)=>{//
//	e.preventDefault();		
//	})
//})

// 부모레게 전달되느 이벤트 버블링	
document.querySelector("tbody").addEventListener("click", (e) => {
	// a 태그 기능 중지
	e.preventDefault();

	// 이벤트 대상
	console.log(e.target);
	console.log(e.target.href);
	console.log(e.target.getAttribute("href"));

	const bno = e.target.getAttribute("href");

	af.querySelector("[name='bno']").value = bno;

	// 삽입 후 확인
	console.log(af.innerHTML);

	// actionForm submit
	// action : /read.do 변경
	af.action = "/cntUpdate.do"
	af.submit();
});

// 검색기능
// cirteria or keyword 입력이 안된 경우 form submit 중지
// 적절한 메세지 띄우기
const wbt = document.querySelector("#searchForm");
wbt.addEventListener("submit", (e) => {
	e.preventDefault();
	const ci = wbt.querySelector("#criteria");
	const kw = wbt.querySelector("#keyword");
	if (ci.value === "n") {
		alert("criteria을 입력해주세요");
		return;
	} else if (kw.value === "") {
		alert("keyword을 입력해주세요");
		return;
	} 

	wbt.submit();

})

// 하단의 페이지 나누기 기능 
// 1 2 3 숫자 누를 때 actionForm subimt
// href 값 가져와서 actionForm의 page요소 값으로 대체
const pt = document.querySelector(".pagination");
console.log(pt);
pt.addEventListener("click",(e)=>{
	e.preventDefault();
	
	af.querySelector("[name='bno']").remove();
	af.querySelector("[name='page']").value = e.target.getAttribute("href");
	af.action="/list.do";
	af.submit();
	
})
