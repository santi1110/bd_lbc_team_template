const routineForm = document.querySelector("#create-routine-form");
const routinesList = document.querySelector("#routines");

routineForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const name = document.querySelector("#routine-name").value;
  const customerId = "testCustomer";
  const routineObj = {
    "name": name,
    "customerId": customerId,
    "exerciseCount": 0
  }
  axios.post("https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines", routineObj, {
    authorization: {
      'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
    }
  }).then((res) => {
    console.log(res);
    window.location.reload();
  })
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Routine Data...");
  axios.get("https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines", {
    authorization: {
      'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
    }
  }).then((res) => {
    console.log(res.data);
    populateRoutines(res.data.routines);
  })
}

function populateRoutines(RoutineData) {

  for (let routine of routineData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(routine.name);

    a.setAttribute('href', `./routine.html?id=${routine.id}`);

    a.appendChild(text);
    li.appendChild(a);
    routinesList.appendChild(li);
  }
}
