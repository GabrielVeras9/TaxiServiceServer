//CONTEUDO RELACIONADO A OCULTARCONTEUDOS
function ocultarConteudos(excecao) {
    const conteudos = [
        'conteudoMonitoracao',

    ];

    conteudos.forEach(conteudo => {
        const elemento = document.getElementById(conteudo);
        if (conteudo !== excecao) {
            elemento.style.display = 'none';
        } else {
            elemento.style.display = 'block'; // Exibir o conteúdo desejado
        }
    });
}