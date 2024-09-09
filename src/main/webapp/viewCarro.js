const getIdFromUrl = () => {
  const params = new URLSearchParams(window.location.search);
  return params.get("id");
};

const formatPrice = (price) => {
  return price.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
};

const fillCarDetails = (data) => {
  const editButton = document.querySelector("#edit-button");
  
  editButton.href = `./adicionarCarro.html?id=${data.id}`;
  const lancamentoTag = data.lancamento 
    ? '<span class="bg-green-100 text-green-800 text-sm font-medium me-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300">LANÇAMENTO</span>' 
    : '';

  document.getElementById("car-model").innerHTML = `${data.marca} ${data.modelo} ${lancamentoTag}`;
  
  document.getElementById("car-price").innerText = formatPrice(data.preco);

  if (data.oferta) {
    document.getElementById("car-discount").innerHTML = `
        <strike id="car-original-price">${formatPrice(data.preco * 1.15)}</strike>
        <span id="offer-text" class="text-sm ml-1">EM OFERTA</span>
      `;
  } else {
    document.getElementById("car-discount").innerText = "";
  }

  const img = document.getElementById("car-image");
  img.src = `data:image/png;base64,${data.imagemBase64}`;

  const detailsList = document.getElementById("car-details");
  detailsList.innerHTML = `
      <li class="text-sm">Categoria <span class="ml-4 float-right">${data.categoria}</span></li>
      <li class="text-sm">Tipo de Combustível <span class="ml-4 float-right">${data.tipoCombustivel}</span></li>
      <li class="text-sm">Ano <span class="ml-4 float-right">${data.anoFabricacao}</span></li>
      <li class="text-sm">Cor <span class="ml-4 float-right">${data.cor}</span></li>
	  <li class="text-sm">KM Total <span class="ml-4 float-right">${data.km.toLocaleString('pt-BR')}</span></li>
    `;
};

const fetchCarDetails = (id) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?action=view&id=${id}`)
    .then((response) => response.json())
    .then((data) => fillCarDetails(data))
    .catch((error) =>
      console.error(`Erro ao visualizar carro (${id}):`, error)
    );
};

const carId = getIdFromUrl();
if (carId) {
  fetchCarDetails(carId);
} else {
  console.error("ID do carro não encontrado na URL");
}

document.querySelector("#delete-button").addEventListener('click', () => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?id=${carId}`, {
    method: "DELETE",
  })
    .then((response) => response.json())
    .then((data) => console.log(`Carro (${carId}) deletado:`, data))
    .catch((error) => console.error(`Erro ao deletar carro (${carId}):`, error))
    .finally(() => {
      window.location.href = './index.html';
    });
});
