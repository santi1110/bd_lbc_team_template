const routineForm = document.querySelector("#create-routine-form");
const routinesList = document.querySelector("#routines");

routineForm.onsubmit = async function(evt) {
  evt.preventDefault();

  const name = document.querySelector("#routine-name").value;
  const customerId = "testCustomer"; // Replace with actual logic for customerId
  const routineObj = {
    name,
    customerId,
    exerciseCount: 0
  };

  try {
    const res = await axios.post("https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines", routineObj, {
      headers: {
        'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
      }
    });
    console.log(res);
    window.location.reload();
  } catch (error) {
    console.error("Error creating routine:", error);
  }
};

window.onload = async function() {
  console.log("Getting Routine Data...");

  try {
    const res = await axios.get("https://xcw9g5k9xa.execute-api.us-west-2.amazonaws.com/prod/routines", {
      headers: {
        'x-api-key': 'kAaNLacHgC1KmpJR5P5yE6cXN6AC1YjB7Yf5PPpC'
      }
    });
    console.log(res.data);
    populateRoutines(res.data.routines);
  } catch (error) {
    console.error("Error fetching routines:", error);
  }
};

function populateRoutines(routineData) {
  routineData.forEach(routine => {
    const li = document.createElement("li");
    const a = document.createElement("a");
    a.textContent = routine.name;
    a.href = `./routine.html?id=${routine.id}`;
    li.appendChild(a);
    routinesList.appendChild(li);
  });
}

