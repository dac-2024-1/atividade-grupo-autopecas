const allButons = document.querySelectorAll('.update-button');
const updateDivs = document.querySelectorAll('.update-div');
allButons.forEach((button, index) => {
    button.addEventListener('click', () => {
        updateDivs[index].classList.toggle('hide');
    });
});