const imgs = document.querySelectorAll('.img-select a');
const imgBtns = [...imgs];
let imgId = 1;

imgBtns.forEach((imgItem) => {
	imgItem.addEventListener('click', (event) => {
		event.preventDefault();
		imgId = imgItem.dataset.id;
		slideImage();
	});
});

function slideImage() {
	const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;

	document.querySelector('.img-showcase').style.transform = 'translateX(${- (imgId - 1) * displayWidth}px)';
}

window.addEventListener('resize', slideImage);


function insBasket(prd_id) {
	let size = document.getElementById("size").value;
	if ('${loginUser}' == '') {
		alert("로그인 후 이용이 가능합니다")
	} else if (size == -1) {
		alert("사이즈가 품절 됬습니다.")
	} else if (size == 0) {
		alert('사이즈를 선택해주세요')
	} else {
		axios.post('/prd/insBasket', null, {
			params: {
				prd_id,
				size
			}
		}).then(function(response) {
			if (response.data == 1) {
				alert("장바구니에 담았습니다.")

			} else {
				alert("이미 장바구니에 담은 상품입니다.")
			}
			if (confirm('장바구니로 이동하시겠습니까?')) {
				location.href = "http://localhost:9123/usr/basket";
			}
		})

	}

}

function cmtInsert(prd_id) {
	let cmt_des = document.getElementById("cmt_des").value;
	axios.get('/prd/insCmt', {
		params: {
			prd_id,
			cmt_des

		}
	}).then(function(res) {
		if (res.data == 0) {
			alert("로그인 안했네요?")
		} else {
			history.go(0)
		}

	})
}

function deleteCmt(cmt_id) {
	axios.get('/prd/delCmt', {
		params: {
			cmt_id

		}
	}).then(function(res) {
		history.go(0)

	})
}


function updateCmt(cmt_id, cmt_des) {
	axios.get('/prd/updateCmt', {
		params: {
			cmt_id,
			cmt_des

		}
	}).then(function(res) {
		if (res.data == 1) {
			saveComment(cmt_id);
		} else {
			alert("수정실패")
		}

	})
}




function changeToInput(cmt_id, cmt_des) {
	var comment = document.getElementById(`comment-${cmt_id}`);
	var editButton = document.getElementById(`edit-${cmt_id}`);

	// Create new input element
	var newInput = document.createElement('input');
	newInput.type = 'text';
	newInput.value = cmt_des;
	newInput.id = `input-${cmt_id}`;

	// Replace comment with input
	comment.parentNode.replaceChild(newInput, comment);

	// Change edit button to save button
	editButton.textContent = '저장';
	editButton.onclick = function() {
		var cmt_des = document.getElementById(`input-${cmt_id}`).value;
		updateCmt(`${cmt_id}`, cmt_des);

	};

	// Create cancel button
	var cancelButton = document.createElement('button');
	cancelButton.textContent = '취소';
	cancelButton.className = "btn btn-warning"
	cancelButton.style.marginLeft = "5px";
	cancelButton.onclick = function() {
		cancelEdit(cmt_id, cmt_des);
	};

	// Add cancel button next to save button
	editButton.parentNode.insertBefore(cancelButton, editButton.nextSibling);
}

function saveComment(cmt_id) {
	var input = document.getElementById(`input-${cmt_id}`);
	var editButton = document.getElementById(`edit-${cmt_id}`);

	// Save new comment
	var newComment = input.value;

	// 이 부분에 서버로 코멘트 수정 요청을 보내는 코드를 추가하세요.

	// Replace input with new comment
	var newCommentSpan = document.createElement('span');
	newCommentSpan.id = `comment-${cmt_id}`;
	newCommentSpan.textContent = newComment;
	input.parentNode.replaceChild(newCommentSpan, input);

	// Change save button back to edit button
	editButton.textContent = '댓글수정';
	editButton.onclick = function() {
		changeToInput(cmt_id, newComment);
	};

	// Remove cancel button
	editButton.parentNode.removeChild(editButton.nextSibling);
}

function cancelEdit(cmt_id, cmt_des) {
	var input = document.getElementById(`input-${cmt_id}`);
	var editButton = document.getElementById(`edit-${cmt_id}`);

	// Replace input with original comment
	var comment = document.createElement('span');
	comment.id = `comment-${cmt_id}`;
	comment.textContent = cmt_des;
	input.parentNode.replaceChild(comment, input);

	// Change save button back to edit button
	editButton.textContent = '댓글수정';
	editButton.onclick = function() {
		changeToInput(cmt_id, cmt_des);
	};

	// Remove cancel button
	editButton.parentNode.removeChild(editButton.nextSibling);
}
function insBasket(prd_id) {
	let size = document.getElementById("size").value;
	if ('${loginUser}' == '') {
		alert("로그인 후 이용이 가능합니다")
	} else if (size == -1) {
		alert("사이즈가 품절 됬습니다.")
	} else if (size == 0) {
		alert('사이즈를 선택해주세요')
	} else {
		axios.post('/prd/insBasket', null, {
			params: {
				prd_id,
				size
			}
		}).then(function(response) {
			if (response.data == 1) {
				alert("장바구니에 담았습니다.")

			} else {
				alert("이미 장바구니에 담은 상품입니다.")
			}
			if (confirm('장바구니로 이동하시겠습니까?')) {
				location.href = "http://localhost:9123/usr/basket";
			}
		})

	}

}

function cmtInsert(prd_id) {
	let cmt_des = document.getElementById("cmt_des").value;
	axios.get('/prd/insCmt', {
		params: {
			prd_id,
			cmt_des

		}
	}).then(function(res) {
		if (res.data == 0) {
			alert("로그인 안했네요?")
		} else {
			document.getElementById("cmt_des").innerHTML = '';
			let cmts = document.getElementById("cmts");

			// 첫 번째 자식 요소인 <div> 추가
			const usr_nm = document.createElement("div");
			usr_nm.innerHTML = '${loginUser.name} : ' + cmt_des
			cmts.appendChild(usr_nm);

			// 두 번째 자식 요소인 <button> 추가
			const changeButton = document.createElement("button");
			changeButton.innerHTML = "댓글수정"
			cmts.appendChild(changeButton);

			const deleteButton = document.createElement("button");
			deleteButton.innerHTML = "댓글삭제"
			deleteButton.onclick = deleteCmt(res.data.cmt_id);

			cmts.appendChild(deleteButton);
		}

	})
}

function deleteCmt() {
	axios.get('/prd/delCmt', {
		params: {
			prd_id,
			cmt_des

		}
	}).then(function(res) {
		if (res.data == 0) {
			alert("로그인 안했네요?")
		} else {

		}

	})
}

