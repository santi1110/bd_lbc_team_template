const addExerciseForm = document.querySelector("#add-exercise-form");
const exerciseTable = document.querySelector("#exercises-table");
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

addExerciseForm.onsubmit = function(evt) {
  evt.preventDefault();
  const exerciseId = document.querySelector("#exercise-id").value;
  const exerciseNumber = document.querySelector("#exercise-number").value;
  const newExercise = {
    "exerciseId": exerciseId,
    "exerciseNumber": exerciseNumber,
    "queueNext": false
  }
  axios.post(`https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines/${id}/trainings`, newExercise, {
    authorization: {
      'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
    }
  })
  .then(res => {
    console.log(res);
    window.location.reload();
  });
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Exercises Data...");
  axios.get("https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines/"+id+"/trainings", {
    authorization: {
      'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
    }
  }).then(res => {
    console.log(res);
    if (!res.data) {
      throw "No data for routine with id:" + id;
    }

    if (res.data.exerciseList.length > 0) {
      populateExercises(res.data.exerciseList);
    }
  })
}

function populateExercises(exercisesData) {
  let thead = exerciseTable.createTHead();
  let tbody = exerciseTable.createTBody();
  let row = thead.insertRow();

  for (let key in exercisesData[0]) {
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }

  for (let exercise of exercisesData) {
    let row = tbody.insertRow();
    for (key in exercise) {
      let cell = row.insertCell();
      let text = document.createTextNode(exercise[key]);
      cell.appendChild(text);
    }
  }
}
