// Uso: node build-tema.js Tema1.docx Tema1.html [t11]
// Requiere: npm i mammoth cheerio
// Ficheros junto al script: style-map.txt (el style map) y styles.css (tu bloque <style> sin las etiquetas)

const fs = require("fs");
const mammoth = require("mammoth");
const cheerio = require("cheerio");

async function main(input, output, cls = "t11") {
  const styleMap = fs.readFileSync("style-map.txt", "utf8");
  const { value, messages } = await mammoth.convertToHtml({ path: input }, { styleMap });
  messages.forEach((m) => console.warn(`[mammoth] ${m.type}: ${m.message}`));

  const $ = cheerio.load(value, null, false);

  // 1. Código: <br /> -> \n dentro de <pre>, y fuera saltos iniciales
  $("pre").each((_, el) => {
    $(el).find("br").replaceWith("\n");
    $(el).text($(el).text().replace(/^\n+/, ""));
  });

  // 2. Tablas dentro de .table-wrap
  $("table").each((_, el) => {
    $(el).wrap('<div class="table-wrap"></div>');
  });

  // 3. Estructura: cabecera del tema + secciones por cada h2
  const nodes = $.root().children().toArray();
  let header = "";
  const parts = [];
  let section = null;

  const closeSection = () => {
    if (section) parts.push(`<div class="section">${section.join("")}</div>`);
    section = null;
  };

  for (const node of nodes) {
    const tag = node.tagName;
    if (tag === "h1") {
      const title = $(node).text().trim();
      const num = (title.match(/\d+/) || [""])[0];
      header = `
<div class="tema-header ${cls}">
  <div class="tema-num">${num}</div>
  <div>
    <div class="tema-kicker">Tema ${num}</div>
    <h1 class="tema-title">${title}</h1>
  </div>
</div>`;
    } else if (tag === "h2") {
      closeSection();
      section = [$.html(node).replace(/^<h2/, '<h2 class="section-title"').replace('class="section-title" class="', 'class="section-title ')];
    } else if (section) {
      section.push($.html(node));
    } else {
      parts.push($.html(node));
    }
  }
  closeSection();

  const html = `<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tema</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Fraunces:ital,wght@0,300;0,600;0,700;0,900;1,300&family=Instrument+Sans:wght@300;400;500;600&family=JetBrains+Mono:wght@400;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="styles.css">
</head>
<body>
<main>
<div class="tema ${cls}">${header}
${parts.join("\n")}
</div>
</main>
</body>
</html>`;

  fs.writeFileSync(output, html, "utf8");
  console.log(`Generado ${output}`);
}

const [, , input, output, cls] = process.argv;
if (!input || !output) {
  console.error("Uso: node build-tema.js entrada.docx salida.html [t11]");
  process.exit(1);
}
main(input, output, cls).catch((e) => {
  console.error(e);
  process.exit(1);
});
