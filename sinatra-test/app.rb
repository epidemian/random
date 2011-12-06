require 'sinatra'
require 'sinatra/reloader' if development?
  
get '/' do
    @recipes = ['Fideos con manteca y queso', 
        'Paties con arroz',
        'Homelette de queso con salchicha']
    erb :index
end

get '/:recipe' do
    @recipe = params[:recipe]
    erb :recipe
end
