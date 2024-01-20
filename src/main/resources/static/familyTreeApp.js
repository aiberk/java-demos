function App() {
  const [familyTree, setFamilyTree] = React.useState([]);
  const [error, setError] = React.useState(null);
  const [personName, setPersonName] = React.useState("");
  const [usingExampleData, setUsingExampleData] = React.useState(false);
  const exampleData = [
    {
      name: "Arthur",
      motherName: "Elizabeth of York",
      fatherName: "Henry VII",
    },
    {
      name: "Henry VIII",
      motherName: "Elizabeth of York",
      fatherName: "Henry VII",
    },
    {
      name: "Margaret",
      motherName: "Elizabeth of York",
      fatherName: "Henry VII",
    },
    {
      name: "Mary",
      motherName: "Elizabeth of York",
      fatherName: "Henry VII",
    },
    {
      name: "Mary I",
      motherName: "Catherine of Aragon",
      fatherName: "Henry VIII",
    },
    {
      name: "Elizabeth I",
      motherName: "Anne Boleyn",
      fatherName: "Henry VIII",
    },
    {
      name: "Edward VI",
      motherName: "Jane Seymour",
      fatherName: "Henry VIII",
    },
    {
      name: "James V",
      motherName: "Margaret",
      fatherName: "James IV",
    },
    {
      name: "Mary, Queen of Scots",
      motherName: "Mary of Guise",
      fatherName: "James V",
    },
    {
      name: "James VI & I",
      motherName: "Mary, Queen of Scots",
      fatherName: "Henry, Lord Darnley",
    },
    {
      name: "Frances",
      motherName: "Mary",
      fatherName: "Charles Brandon",
    },
    {
      name: "Lady Jane Grey",
      motherName: "Frances",
      fatherName: "Henry Grey",
    },
    {
      name: "Henry, Lord Darnley",
      motherName: "Margaret Stuart",
      fatherName: "unknown",
    },
    {
      name: "Margaret Stuart",
      motherName: "Margaret",
      fatherName: "unknown",
    },
  ];
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

  const handleUseExampleData = async (e) => {
    e.preventDefault();
    setUsingExampleData(true);
    for (const person of exampleData) {
      const formData = new FormData();
      formData.append("name", person.name);
      formData.append("motherName", person.motherName);
      formData.append("fatherName", person.fatherName);

      await fetch("/tree/addPerson", {
        method: "POST",
        body: formData,
      });
    }
    updateTree(); // Update the family tree after adding all persons
  };

  // Function to handle submitting the person selection form
  const handleSelectPerson = (e) => {
    e.preventDefault();
    updateFamilyTree(personName);
  };

  // Initial fetch of family tree
  // React.useEffect(() => {
  //   updateFamilyTree("Arthur");
  // }, []);

  return (
    <div>
      {!usingExampleData && (
        <div>
          <button onClick={handleUseExampleData}>Use Example Data</button>
          <FamilyTreeForm updateTree={updateFamilyTree} />{" "}
        </div>
      )}
      {usingExampleData && (
        <h4>Try searching for Arthur, Henry VIII, or Margaret</h4>
      )}

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
      <h4>Or add family members</h4>
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
      <button type="submit" style={{ marginRight: "1rem" }}>
        Add Person
      </button>
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
