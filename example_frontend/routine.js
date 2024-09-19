const addExerciseForm = document.querySelector("#add-exercise-form");
const exerciseTable = document.querySelector("#exercises-table");
const urlParams = new URLSearchParams(window.location.search);
const routineId = urlParams.get('id');

addExerciseForm.onsubmit = async function(evt) {
  evt.preventDefault();

  const exerciseId = document.querySelector("#exercise-id").value;
  const exerciseNumber = document.querySelector("#exercise-number").value;

  const newExercise = {
    exerciseId,
    exerciseNumber,
    queueNext: false
  };

  try {
    const res = await axios.post(`https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines/${routineId}/trainings`, newExercise, {
      headers: {
        'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
      }
    });
    console.log(res);
    window.location.reload();
  } catch (error) {
    console.error("Error adding exercise:", error);
  }
};

window.onload = async function() {
  console.log("Getting Exercises Data...");

  try {
    const res = await axios.get(`https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines/${routineId}/trainings`, {
      headers: {
        'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
      }
    });
    if (!res.data) throw new Error(`No data for routine with id: ${routineId}`);

    if (res.data.exerciseList.length > 0) {
      populateExercises(res.data.exerciseList);
    }
  } catch (error) {
    console.error("Error fetching exercises:", error);
  }
};

function populateExercises(exercisesData) {
  const thead = exerciseTable.createTHead();
  const tbody = exerciseTable.createTBody();
  const row = thead.insertRow();

  Object.keys(exercisesData[0]).forEach(key => {
    const th = document.createElement("th");
    th.textContent = key;
    row.appendChild(th);
  });

  exercisesData.forEach(exercise => {
    const row = tbody.insertRow();
    Object.values(exercise).forEach(value => {
      const cell = row.insertCell();
      cell.textContent = value;
    });
  });
}

