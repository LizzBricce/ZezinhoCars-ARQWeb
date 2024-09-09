const cardCarroComponent = (carData) => {
  const cardDiv = document.createElement("div");
  cardDiv.classList.add(
    "w-full",
    "md:w-1/3",
    "xl:w-1/4",
    "p-6",
    "flex",
    "flex-col"
  );

  const linkElement = document.createElement("a");
  linkElement.href = `./visualizarCarro.html?id=${carData.id}`;

  const imgElement = document.createElement("img");
  imgElement.classList.add("hover:grow", "hover:shadow-lg");
  imgElement.width = 400;
  imgElement.height = 400;
  imgElement.src = `data:image/jpeg;base64,${carData.imagemBase64}`;

  const nameDiv = document.createElement("div");
  nameDiv.classList.add("pt-3", "flex", "items-center", "justify-between");

  const nameP = document.createElement("p");
  nameP.textContent = carData.modelo;

  nameDiv.appendChild(nameP);

  const formattedPrice = new Intl.NumberFormat("pt-BR", {
    style: "currency",
    currency: "BRL",
  }).format(carData.preco);

  const priceP = document.createElement("p");
  priceP.classList.add("pt-1", "text-gray-900");
  priceP.textContent = formattedPrice;

  linkElement.appendChild(imgElement);
  linkElement.appendChild(nameDiv);
  linkElement.appendChild(priceP);

  cardDiv.appendChild(linkElement);

  return cardDiv;
};

const fetchCarList = () => {
  fetch("http://localhost:8080/CRUD-Carros/carro?action=list")
    .then((response) => response.json())
    .then((data) => {
      const container = document.querySelector(".carros-cotainer");
	  container.innerHTML = "";
      data.forEach((car) => {
        const card = cardCarroComponent(car);
        container.appendChild(card);
      });
      montaCarrousel(data);
    })
    .catch((error) => console.error("Erro ao listar carros:", error));
};

fetchCarList();

const carouselCarroComponent = (carData, index) => {
  const inputElement = document.createElement("input");
  inputElement.classList.add("carousel-open");
  inputElement.type = "radio";
  inputElement.id = `carousel-${index + 1}`;
  inputElement.name = "carousel";
  inputElement.hidden = true;
  if (index === 0) inputElement.checked = true;

  const carouselItem = document.createElement("div");
  carouselItem.classList.add("carousel-item", "absolute", "opacity-0");
  carouselItem.style.height = "50vh";

  const bgDiv = document.createElement("div");
  bgDiv.classList.add(
    "block",
    "h-full",
    "w-full",
    "mx-auto",
    "flex",
    "pt-6",
    "md:pt-0",
    "md:items-center",
    "bg-cover",
    "bg-right"
  );
  bgDiv.style.backgroundImage = `url(data:image/jpeg;base64,${carData.imagemBase64})`;

  const overlayDiv = document.createElement("div");
  overlayDiv.style.position = "absolute";
  overlayDiv.style.top = "0";
  overlayDiv.style.left = "0";
  overlayDiv.style.width = "100%";
  overlayDiv.style.height = "100%";
  overlayDiv.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
  overlayDiv.style.zIndex = "1";

  const containerDiv = document.createElement("div");
  containerDiv.classList.add("container", "mx-auto");
  containerDiv.style.position = "relative";

  const textDiv = document.createElement("div");
  textDiv.classList.add(
    "flex",
    "flex-col",
    "w-full",
    "lg:w-1/2",
    "md:ml-16",
    "items-center",
    "md:items-start",
    "px-6",
    "tracking-wide"
  );
  textDiv.style.position = "relative"; 

  const nameP = document.createElement("p");
  nameP.classList.add("text-white", "text-2xl", "my-4");
  nameP.textContent = carData.nome;

  const linkElement = document.createElement("a");
  linkElement.classList.add(
    "text-xl",
    "inline-block",
    "no-underline",
    "border-b",
    "border-gray-600",
    "leading-relaxed",
    "hover:text-white",
    "hover:border-white"
  );
  linkElement.style.color = "white";
  linkElement.style.zIndex = "10";
  linkElement.href = `./visualizarCarro.html?id=${carData.id}`;
  linkElement.textContent = "Visualizar Carro em Destaque";

  textDiv.appendChild(nameP);
  textDiv.appendChild(linkElement);

  containerDiv.appendChild(textDiv);
  bgDiv.appendChild(overlayDiv);
  bgDiv.appendChild(containerDiv);
  carouselItem.appendChild(bgDiv);

  return { inputElement, carouselItem };
};

const createCarouselIndicators = (numItems) => {
  const indicators = document.createElement("ol");
  indicators.classList.add("carousel-indicators");

  for (let i = 0; i < numItems; i++) {
    const li = document.createElement("li");
    li.classList.add("inline-block", "mr-3");

    const label = document.createElement("label");
    label.classList.add(
      "carousel-bullet",
      "cursor-pointer",
      "block",
      "text-4xl",
      "text-gray-400",
      "hover:text-gray-900"
    );
    label.setAttribute("for", `carousel-${i + 1}`);
    label.textContent = "•";

    li.appendChild(label);
    indicators.appendChild(li);
  }

  return indicators;
};

const montaCarrousel = (data) => {
  const carouselContainer = document.querySelector(".carousel-inner");

  const destaqueCars = Array.isArray(data)
    ? data.filter((car) => car.destaque)
    : [];

  destaqueCars.forEach((car, index) => {
    const { inputElement, carouselItem } = carouselCarroComponent(car, index);
    carouselContainer.appendChild(inputElement);
    carouselContainer.appendChild(carouselItem);
  });

  const indicators = createCarouselIndicators(destaqueCars.length);
  carouselContainer.appendChild(indicators);
};


const fetchCarrosByModel = (modelo) => {
  fetch(`http://localhost:8080/CRUD-Carros/carro?action=searchByModel&modelo=${modelo}`)
    .then((response) => response.json())
    .then((data) => {
      const container = document.querySelector(".carros-cotainer");
      container.innerHTML = "";
      data.forEach((car) => {
        const card = cardCarroComponent(car);
        container.appendChild(card);
      });
    })
    .catch((error) => console.error("Erro ao buscar carros por modelo:", error));
};

document.getElementById("search-button").addEventListener("click", () => {
  const modelo = document.getElementById("simple-search").value.trim();
  console.log(modelo);
  if (modelo) {
    fetchCarrosByModel(modelo);
  } else {
    fetchCarList();
  }
});
