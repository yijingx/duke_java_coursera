var dd1 = document.getElementById("c1");


function changeColor(){
  dd1.style.backgroundColor = "blue";
}

function addEle(){
  ctx = dd1.getContext("2d");
  ctx.fillStyle = "yellow";
  ctx.fillRect(10,10,60,60);
  ctx.fillStyle = "black";
  ctx.font = "20px Arial";
  ctx.fillText("hello",15,45);
}