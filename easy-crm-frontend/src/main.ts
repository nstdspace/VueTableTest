import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import VueKeycloak from '@dsb-norge/vue-keycloak-js'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(VueKeycloak, {
  config: {
    url: 'http://localhost:8180',
    realm: 'easy-crm',
    clientId: 'easy-crm-frontend',
  },
  init: {
    onLoad: 'login-required',
    checkLoginIframe: false,
  },
  onReady: () => app.mount('#app'),
})
app.use(createPinia())
app.use(router)
