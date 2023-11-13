function validateForm() {
    // 폼 유효성 검사 로직을 추가할 수 있습니다.
    return true; // 예시로 항상 true 반환
}
function fetchData() {
    const form = document.querySelector('form');
    fetch('/wetro/api', {
        method: 'POST',
        body: new FormData(form),
    })
        .then(response => response.json())
        .then(data => {
            const click = document.querySelector('form a');
            const routeInfo = {출발역:data.from,도착역:data.to}
            click.addEventListener('submit',()=> {
                localStorage.setItem("route-info",JSON.stringify(routeInfo))
            })
        })
        .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    const click = document.querySelector('form a');
    click.addEventListener('submit', (event) => {
        event.preventDefault(); // 주석 해제
        if (validateForm()) {
            console.log('Form is valid');
            fetch('/wetro/api', {
                method: 'POST',
                body: new FormData(click),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Data received from server:', data);
                    fetchData();
                })
                .catch(error => console.error('Error:', error));
        } else {
            console.log('Form is not valid');
        }
    });
});
