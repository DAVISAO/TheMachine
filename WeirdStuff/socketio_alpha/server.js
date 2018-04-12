
const JAVA_RUN = '' // JAVA EXEC
const PORT = 5999

const express = require('express');
const http = require('http');
const { exec } = require('child_process');

const app = http.createServer(serverHandler)
const io = require('socket.io')(app)

function slog(x) {
    console.log('[server.js] ' + x)
}

function serverHandler(req, res) {
    // nothing
}


io.on('connection', function (client) {
  slog('CLIENT CONNECTED')
});

slog('Listening on ' + PORT)
app.listen(PORT)

slog('Executing JAVA_RUN')
if (JAVA_RUN && JAVA_RUN != '') exec(JAVA_RUN, (err, out, stderr) => {
    if (out) slog(' ==== OUT ====\n' + out)
    if (err) slog(' ==== ERR ====\n' + err)
    if (stderr) slog(' ==== STDERR ====\n' + stderr)
})

slog('Reached script end')