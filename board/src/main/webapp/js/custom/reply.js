// 목록 클릭시 actionFom submit
const aff = document.querySelector("#actionForm");

document.querySelector("#reply .btn-success").addEventListener("click", () => {
	aff.action = "/list.do";
	aff.submit();
})