const { app, BrowserWindow } = require('electron');
const { spawn, exec } = require('child_process');
const path = require('path');





let mainWindow;
let springBootProcess;

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1000,
    height: 800,
    webPreferences: { nodeIntegration: true }
  });

  mainWindow.loadFile("index.html");


  mainWindow.on('closed', () => {
    mainWindow = null;
  });
}

function startSpringBoot() {
  const jarPath = path.join(__dirname, './target/todoapp-0.0.1-SNAPSHOT.jar');
  springBootProcess = spawn('java', ['-jar', jarPath], {
    cwd: path.dirname(jarPath),
    shell: true
  });

  springBootProcess.stdout.on('data', data => console.log(`SpringBoot: ${data}`));
  springBootProcess.stderr.on('data', data => console.error(`SpringBoot ERR: ${data}`));

  springBootProcess.on('close', code => {
    console.log(`Spring Boot terminó con código ${code}`);
  });
}

function stopSpringBoot() {
  if (springBootProcess && !springBootProcess.killed) {
    try {
      process.kill(springBootProcess.pid, 'SIGTERM');
    } catch (err) {
      console.error('Error cerrando con SIGTERM, probamos con taskkill');
    }

    exec(`taskkill /PID ${springBootProcess.pid} /F /T`, (err) => {
      if (err) console.error('Error forzando cierre de JVM:', err);
      else console.log('Spring Boot matado con taskkill');
    });
  }
}

app.on('ready', () => {
  startSpringBoot();
  createWindow();
});

app.on('before-quit', () => {
  stopSpringBoot();
  setTimeout(() => {
    process.exit(0);  
  }, 1000);
});


app.on('window-all-closed', () => {
  app.quit();
});
