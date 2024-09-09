const fetchCarList = () => {
  fetch("http://localhost:8080/CRUD-Carros/carro?action=list")
    .then((response) => response.json())
    .then((data) => console.log("Lista de Carros:", data))
    .catch((error) => console.error("Erro ao listar carros:", error));
};


const fetchCarDetails = (id) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?action=view&id=${id}`)
    .then((response) => response.json())
    .then((data) => console.log(`Detalhes do Carro (${id}):`, data))
    .catch((error) => console.error(`Erro ao visualizar carro (${id}):`, error));
};

const addCarWithImage = (carData, imageFile) => {
  const formData = new FormData();

  formData.append("avaliacao", carData.avaliacao);
  formData.append("preco", carData.preco);
  formData.append("km", carData.km);
  formData.append("categoria", carData.categoria);
  formData.append("marca", carData.marca);
  formData.append("modelo", carData.modelo);
  formData.append("anoFabricacao", carData.anoFabricacao);
  formData.append("cor", carData.cor);
  formData.append("tipoCombustivel", carData.tipoCombustivel);
  formData.append("destaque", carData.destaque);
  formData.append("lancamento", carData.lancamento);
  formData.append("oferta", carData.oferta);

  if (imageFile) {
    formData.append("imagem", imageFile);
  }

  fetch("http://localhost:8080/CRUD-Carros/carro?action=add", {
    method: "POST",
    body: formData,
  })
    .then((response) => response.json())
    .then((data) => console.log("Carro com imagem adicionado:", data))
    .catch((error) => console.error("Erro ao adicionar carro com imagem:", error));
};

const editCarWithImage = (id, carData, imageFile) => {
  const formData = new FormData();

  formData.append("avaliacao", carData.avaliacao);
  formData.append("preco", carData.preco);
  formData.append("km", carData.km);
  formData.append("categoria", carData.categoria);
  formData.append("marca", carData.marca);
  formData.append("modelo", carData.modelo);
  formData.append("anoFabricacao", carData.anoFabricacao);
  formData.append("cor", carData.cor);
  formData.append("tipoCombustivel", carData.tipoCombustivel);
  formData.append("destaque", carData.destaque);
  formData.append("lancamento", carData.lancamento);
  formData.append("oferta", carData.oferta);

  if (imageFile) {
    formData.append("imagem", imageFile);
  }

  fetch(`http://localhost:8080/CRUD-Carros/carro?action=edit&id=${id}`, {
    method: "POST",
    body: formData,
  })
    .then((response) => response.json())
    .then((data) => console.log(`Carro (${id}) editado:`, data))
    .catch((error) => console.error(`Erro ao editar carro (${id}):`, error));
};


const deleteCar = (id) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?id=${id}`, {
    method: "DELETE",
  })
    .then((response) => response.json())
    .then((data) => console.log(`Carro (${id}) deletado:`, data))
    .catch((error) => console.error(`Erro ao deletar carro (${id}):`, error));
};


const fetchCarDetailsWithImage = (id) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?action=view&id=${id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(`Detalhes do Carro (${id}):`, data);
    })
    .catch((error) => console.error(`Erro ao visualizar carro (${id}):`, error));
};

