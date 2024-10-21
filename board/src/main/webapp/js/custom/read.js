// 목록 클릭시
// actionForm action="list.do" 수정 후 submit
const aff = document.querySelector("#actionForm");
document.querySelector("#readForm .btn-success").addEventListener("click", () => {
	// actionForm bno 요소 제거
	aff.querySelector("[name='bno']").remove();

	aff.action = "/list.do";
	aff.submit();
})

// read.jsp 에서 수정 클릭시
// actionForm action="modify.do" 수정 후 submit
document.querySelector("#readForm .btn-primary").addEventListener("click", () => {
	// 값이 있다면 실행
	aff.action = "/modify.do";
	aff.submit();
})

// modify.jsp 에서 수정 클릭시 (submit) => sunbmit 중지
// readForm password,title,content 값이 있는 지확인하고 
// 없으면 msg 띄우고, 있으면 submit
const rff = document.querySelector("#readForm");
rff.addEventListener("submit", (e) => {
	e.preventDefault();
	const ti = rff.querySelector("#title");
	const ct = rff.querySelector("#content");
	const ps = rff.querySelector("#password");
	if (ti.value === "") {
		alert("제목을 입력해주세요");
		ti.focus();
		return;
	} else if (ct.value === "") {
		alert("내용을 입력해주세요");
		ct.focus();
		return;
	} else if (ps.value === "") {
		alert("비밀번호를 입력해주세요");
		ps.focus();
		return;
	}
})

// 삭제 클릭 시
// readForm actoin => delete.do 변경 후 submit
document.querySelector(".btn-danger").addEventListener("click", () => {
	rff.action = "/delete.do";
	rff.submit();
})


