import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:9999', // URL do load balancer (Nginx)
});

export default api;