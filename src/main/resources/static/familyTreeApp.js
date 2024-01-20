function App() {
  const [familyTree, setFamilyTree] = React.useState([]);
  const [error, setError] = React.useState(null);
  const [personName, setPersonName] = React.useState("");

  // Function to update the family tree
  const updateFamilyTree = (name) => {
    fetch(`/tree/familyTree?name=${name}`) // Adjust the query parameter as needed
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        console.log("response", data);
        setFamilyTree(data);
      })
      .catch((error) => {
        console.error("There was a problem with the fetch operation:", error);
        setError(error.message);
      });
  };

  // Function to handle submitting the person selection form
  const handleSelectPerson = (e) => {
    e.preventDefault();
    updateFamilyTree(personName);
  };

  // Initial fetch of family tree
  React.useEffect(() => {
    updateFamilyTree("test");
  }, []);

  return (
    <div>
      <FamilyTreeForm updateTree={() => updateFamilyTree(personName)} />
      <form onSubmit={handleSelectPerson}>
        <label>
          Person Name:
          <input
            type="text"
            value={personName}
            onChange={(e) => setPersonName(e.target.value)}
          />
        </label>
        <button type="submit">Show Family Tree</button>
      </form>
      <FamilyTree familyTree={familyTree} error={error} />
    </div>
  );
}

/**
 * Fucntion to add a person to the family tree
 * @param {*} param0
 * @returns
 */
function FamilyTreeForm({ updateTree }) {
  const [name, setName] = React.useState("");
  const [motherName, setMotherName] = React.useState("");
  const [fatherName, setFatherName] = React.useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("name", name);
    formData.append("fatherName", fatherName);
    formData.append("motherName", motherName);

    console.log("Submitting:", { name, motherName, fatherName });

    for (let pair of formData.entries()) {
      console.log(`${pair[0]}: ${pair[1]}`);
    }

    fetch("/tree/addPerson", {
      method: "POST",
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response.text();
        }
        throw new Error("Network response was not ok.");
      })
      .then(() => {
        setName("");
        setMotherName("");
        setFatherName("");

        // Update the family tree
        updateTree();
      })
      .catch((error) => {
        console.error("There was a problem with the fetch operation:", error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </label>
      <label>
        Mother's Name:
        <input
          type="text"
          value={motherName}
          onChange={(e) => setMotherName(e.target.value)}
        />
      </label>
      <label>
        Father's Name:
        <input
          type="text"
          value={fatherName}
          onChange={(e) => setFatherName(e.target.value)}
        />
      </label>
      <button type="submit">Add Person</button>
    </form>
  );
}

function FamilyTree({ familyTree, error }) {
  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h2>Family Tree</h2>
      <ul>
        {familyTree.map((member, index) => (
          <li key={index}>{member}</li>
        ))}
      </ul>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("react-root"));
