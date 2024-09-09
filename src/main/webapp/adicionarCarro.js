const form = document.querySelector("form");
const fileInput = document.querySelector("#dropzone-file");
const imagePreview = document.querySelector("#image-preview");
const formAction = document.querySelector("#formAction");
const carIdInput = document.querySelector("#carId");

const loadCarData = (id) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?action=view&id=${id}`)
    .then((response) => response.json())
    .then((car) => {
      form.querySelector("#avaliacao").value = car.avaliacao;
      form.querySelector("#preco").value = car.preco;
      form.querySelector("#km").value = car.km;
      form.querySelector("#categoria").value = car.categoria;
      form.querySelector("#marca").value = car.marca;
      form.querySelector("#modelo").value = car.modelo;
      form.querySelector("#anoFabricacao").value = car.anoFabricacao;
      form.querySelector("#cor").value = car.cor;
      form.querySelector("#tipoCombustivel").value = car.tipoCombustivel;
      form.querySelector("#destaque").checked = car.destaque === "true";
      form.querySelector("#lancamento").checked = car.lancamento === "true";
      form.querySelector("#oferta").checked = car.oferta === "true";
	
      if (car.imagemBase64) {
        imagePreview.src = "data:image/png;base64," + car.imagemBase64;
      }

      formAction.value = "edit";
      carIdInput.value = car.id;
    })
    .catch((error) => console.error("Erro ao carregar dados do carro:", error));
};

const urlParams = new URLSearchParams(window.location.search);
const carId = urlParams.get("id");

if (carId) {
  loadCarData(carId);
}

fileInput.addEventListener("change", () => {
  const file = fileInput.files[0];
  if (file) {
    const reader = new FileReader();

    reader.onload = (e) => {
      imagePreview.src = e.target.result;
    };

    reader.readAsDataURL(file);
  } else {
    imagePreview.src = "";
  }
});

form.addEventListener("submit", (event) => {
  event.preventDefault();

  const carData = {
    avaliacao: form.querySelector("#avaliacao").value,
    preco: form.querySelector("#preco").value,
    km: form.querySelector("#km").value,
    categoria: form.querySelector("#categoria").value,
    marca: form.querySelector("#marca").value,
    modelo: form.querySelector("#modelo").value,
    anoFabricacao: form.querySelector("#anoFabricacao").value,
    cor: form.querySelector("#cor").value,
    tipoCombustivel: form.querySelector("#tipoCombustivel").value,
    destaque: form.querySelector("#destaque").checked ? "true" : "false",
    lancamento: form.querySelector("#lancamento").checked ? "true" : "false",
    oferta: form.querySelector("#oferta").checked ? "true" : "false",
  };

  const imageFile = fileInput.files[0];
  const action = formAction.value;
  const carId = carIdInput.value;
  const url = action === "edit" ? `http://localhost:8080/CRUD-Carros/carro?action=edit&id=${carId}` : "http://localhost:8080/CRUD-Carros/carro";
  const method = "POST";

  const formData = new FormData();
  Object.keys(carData).forEach((key) => formData.append(key, carData[key]));
  if (imageFile) {
    formData.append("imagem", imageFile);
  }

  fetch(url, {
    method: method,
    body: formData,
  })
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((error) => console.error("Erro ao salvar carro:", error))
	.finally(() => {
		if(carId) {
			window.location.href = `./visualizarCarro.html?id=${carId}`;
		} else {

			window.location.href = './index.html';
		}
	});
});
